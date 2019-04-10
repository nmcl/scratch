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
 * Copyright (C) 2001, 2002,
 *
 * Hewlett-Packard Arjuna Labs,
 * Newcastle upon Tyne,
 * Tyne and Wear,
 * UK.
 *
 * $Id: JTATest.java 2342 2006-03-30 13:06:17Z  $
 */

import javax.transaction.xa.XAResource;

public class BasicJTA
{
    public static void main (String[] args)
    {
	try
	{
	    boolean tmCommit = true;

	    XAResource theResource = new DummyXA(true);
	    XAResource theResource2 = new DummyXA(true);

	    javax.transaction.TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

	    tm.begin();

	    javax.transaction.Transaction theTransaction = tm.getTransaction();

	    /*
	     * XA does not support subtransactions.
	     * By default we ignore any attempts to create such
	     * transactions. Appropriate settings can be made which
	     * will cause currently running transactions to also
	     * rollback, if required.
	     */

	    System.out.println("\nTrying to start another transaction - should fail!");

	    try
	    {
		tm.begin();

		System.err.println("Error - transaction started!");
	    }
	    catch (Exception e)
	    {
		System.out.println("Transaction did not begin: " + e);
	    }

	    System.out.println("\nCommitting transaction.");

	    if (tmCommit)
		tm.commit();
	    else
		tm.getTransaction().commit();
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }
}
