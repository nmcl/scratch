#!/bin/bash
if [[ $(uname) == CYGWIN* ]]
then
  read -p "ARE YOU RUNNING AN ELEVATED CMD PROMPT mvn.cmd needs this" ELEV
  if [[ $ELEV == n* ]]
  then
    exit
  fi
fi
read -p "You will need: VPN, credentials for jbosstm@filemgmt, jira admin, github permissions on all jbosstm/ repo. Do you have these?" ENVOK
if [[ $ENVOK == n* ]]
then
  exit
fi
if [ $# -lt 3 ]; then
  echo 1>&2 "$0: not enough arguments: CURRENT_VERSION_IN_WFLY CURRENT NEXT <WFLYISSUE>(versions should end in .Final or similar)"
  grep 5.2.10.Final pom.xml
  exit 2
elif [ $# -gt 4 ]; then
  echo 1>&2 "$0: too many arguments: CURRENT_VERSION_IN_WFLY CURRENT NEXT (versions should end in .Final or similar)"
  grep 5.2.10.Final pom.xml
  exit 2
else
  WFLYISSUE=$4
fi

set -e
export CURRENT_VERSION_IN_WFLY=$1
export CURRENT=$2
export NEXT=$3
if [ -z "$WFLYISSUE" ]
then
  JENKINS_JOBS=narayana,narayana-catelyn,narayana-codeCoverage,narayana-documentation,narayana-hqstore,narayana-ibm-jdk,narayana-jdbcobjectstore,narayana-quickstarts,narayana-quickstarts-catelyn ./scripts/release/pre_release.py
  ./scripts/release/update_jira.py -k JBTM -t 5.next -n $CURRENT
  read -p "Enter WFLY issue: " WFLYISSUE
  if [ ! -d "jboss-as" ]
  then
    (git clone git@github.com:jbosstm/jboss-as.git -o jbosstm; cd jboss-as; git remote add upstream-wildfly git@github.com:wildfly/wildfly.git)
  fi
  (cd jboss-as; git fetch upstream-wildfly; git checkout -b ${WFLYISSUE}; git reset --hard upstream-wildfly/master)
  cd jboss-as
  if [[ $(uname) == CYGWIN* ]]
  then
    sed -i "s/narayana>$CURRENT_VERSION_IN_WFLY/narayana>$CURRENT/g" pom.xml
  else
    sed -i "" "s/narayana>$CURRENT_VERSION_IN_WFLY/narayana>$CURRENT/g" pom.xml
  fi
  git add pom.xml
  git commit -m "${WFLYISSUE} Upgrade Narayana to $CURRENT"
  git push --set-upstream jbosstm ${WFLYISSUE}
  git fetch jbosstm
  git checkout 5_BRANCH
  git reset --hard jbosstm/5_BRANCH
  if [[ $(uname) == CYGWIN* ]]
  then
    sed -i "s/narayana>.*</narayana>$NEXT</g" pom.xml
  else
    sed -i $SED_EXTRA_ARG "s/narayana>.*</narayana>$NEXT</g" pom.xml
  fi
  git add pom.xml
  git commit -m "Update to latest version of Narayana"
  git push
  cd -
fi
set +e
git fetch upstream --tags
git tag | grep $CURRENT
if [[ $? != 0 ]]
then
  set -e
  (cd ./scripts/ ; ./pre-release.sh $CURRENT $NEXT)
  git fetch upstream --tags
else
  set -e
fi

git checkout $CURRENT; MAVEN_OPTS="-XX:MaxPermSize=512m" 
cd ~/tmp/narayana/$CURRENT/sources/narayana/
git checkout $CURRENT
mvn clean -gs tools/maven/conf/settings.xml -Dorson.jar.location=./ext/
mvn clean deploy -DskipTests -gs tools/maven/conf/settings.xml -Dorson.jar.location=./ext/ -Prelease
mvn clean deploy -DskipTests -gs tools/maven/conf/settings.xml -Prelease -f blacktie/utils/cpp-plugin/pom.xml
mvn clean deploy -DskipTests -gs tools/maven/conf/settings.xml -Prelease  -f blacktie/pom.xml -pl :blacktie-jatmibroker-nbf -am
git archive -o ../../narayana-full-$CURRENT-src.zip $CURRENT
cd -
cd ~/tmp/narayana/$CURRENT/sources/documentation/
git checkout $CURRENT
mvn clean install -Prelease
cd -
ant -f build-release-pkgs.xml -Dawestruct.executable="awestruct" all

echo "build and retrieve the centos54x64 and vc9x32 binaries from http://narayanaci1.eng.hst.ams2.redhat.com/view/Release/"
echo "Press enter when the artifacts are available"
read
wget http://narayanaci1.eng.hst.ams2.redhat.com/view/Release/job/release-narayana/lastSuccessfulBuild/artifact/blacktie/blacktie/target/blacktie-${CURRENT}-centos54x64-bin.tar.gz
wget http://narayanaci1.eng.hst.ams2.redhat.com/view/Release/job/release-narayana-catelyn/lastSuccessfulBuild/artifact/blacktie/blacktie/target/blacktie-${CURRENT}-vc9x32-bin.zip
scp blacktie-${CURRENT}-centos54x64-bin.tar.gz jbosstm@filemgmt.jboss.org:/downloads_htdocs/jbosstm/${CURRENT}/binary/
scp blacktie-${CURRENT}-vc9x32-bin.zip jbosstm@filemgmt.jboss.org:/downloads_htdocs/jbosstm/${CURRENT}/binary/