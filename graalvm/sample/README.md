Same based on https://www.graalvm.org/docs/getting-started/

Blender:

javac Blender.java

Then java -XX:-UseJVMCICompiler Blender to disable the Graal compiler

Do the same for Blender_mod.java

CountUppercase:

javac CountUppercase.java

Then java CountUppercase In 2019 I would like to run ALL languages in one VM.

Then java -Dgraal.PrintCompilation=true CountUppercase In 2019 I would like to run ALL languages in one VM.

Then java -XX:-UseJVMCICompiler CountUppercase In 2019 I would like to run ALL languages in one VM.

HelloWorld:

javac HelloWorld.java

native-image HelloWorld

Then helloworld

UserDir:

javac UserDir.java

Then native-image UserDir

Then userdir