/*
 * JBoss, Home of Professional Open Source
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2005-2006,
 * @author JBoss Inc.
 */

/*
 * Copyright (C) 1998, 1999, 2000,
 *
 * Arjuna Solutions Limited,
 * Newcastle upon Tyne,
 * Tyne and Wear,
 * UK.
 *
 * $Id: BadAction.java 2342 2006-03-30 13:06:17Z  $
 */

import com.arjuna.ats.arjuna.common.*;
import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.arjuna.coordinator.BasicAction;
import com.arjuna.ats.arjuna.coordinator.AbstractRecord;
import com.arjuna.ats.arjuna.coordinator.SynchronizationRecord;
import com.arjuna.ats.arjuna.coordinator.TwoPhaseOutcome;

public class Performance3
{
    public static void main (String[] args)
    {
	arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreSync(false);
	arjPropertyManager.getObjectStoreEnvironmentBean().setTransactionSync(false);

	int numberOfTransactions = 1000;
	long startTime = System.currentTimeMillis();
	
	for (int i = 0; i < numberOfTransactions; i++)
	{
	    AtomicAction A = new AtomicAction();

	    A.begin();
	    A.add(new SimpleAbstractRecord());
	    A.add(new SimpleAbstractRecord());
	    A.addSynchronization(new SyncRecord());
	    A.commit();
	}

	long finalTime = System.currentTimeMillis();

	System.out.println("Time to create, enlist participants and synchronization and commit "+numberOfTransactions+" transactions is "+(finalTime - startTime)+" milliseconds.");
    }
}

class SimpleAbstractRecord extends AbstractRecord
{
    private boolean wasCommitted;

    public int typeIs() {
	return 0;
    }

    public boolean wasCommitted() {
	return wasCommitted;
    }

    public Object value() {
	return null;
    }

    public void setValue(Object o) {
    }

    public int nestedAbort() {
	return TwoPhaseOutcome.FINISH_OK;
    }

    public int nestedCommit() {
	return TwoPhaseOutcome.FINISH_OK;
    }

    public int nestedPrepare() {
	return TwoPhaseOutcome.PREPARE_OK;
    }

    public int topLevelAbort() {
	return TwoPhaseOutcome.FINISH_OK;
    }

    public int topLevelCommit() {
	wasCommitted = true;
	return TwoPhaseOutcome.FINISH_OK;
    }

    public int topLevelPrepare() {
	return TwoPhaseOutcome.PREPARE_OK;
    }

    public void merge(AbstractRecord a) {
    }

    public void alter(AbstractRecord a) {
    }

    public boolean shouldAdd(AbstractRecord a) {
	return false;
    }

    public boolean shouldAlter(AbstractRecord a) {
	return false;
    }

    public boolean shouldMerge(AbstractRecord a) {
	return false;
    }

    public boolean shouldReplace(AbstractRecord a) {
	return false;
    }
}


class SyncRecord implements SynchronizationRecord
{
    public boolean beforeCompletion () {
	return true;
    }

    public boolean afterCompletion (int status) {
	return true;
    }

    public boolean isInterposed () {
	return false;
    }

    public Uid get_uid () {
	return _uid;
    }

    public int compareTo (Object o) {
	return 0;
    }

    private Uid _uid = new Uid();
}