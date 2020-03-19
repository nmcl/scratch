Various examples to try out Excelsior JET. Don't look here for much structure of refactoring - it's quick and dirty!

Note, frozen now in preference to GraalVM efforts.

----

Persistent testing shows for non-compiled (Excelsior):

Apr 26, 2017 8:45:52 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 61581 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Created persistent object 0:ffffa9fe63c9:f08c:5900f8f0:0
Recreated object 0:ffffa9fe63c9:f08c:5900f8f0:0
Final value: 4
Time taken: 9036 milliseconds

Excelsior implementation:

Apr 26, 2017 8:50:17 PM <unknown> <unknown>
INFO: ARJUNA012170: TransactionStatusManager started on port 61649 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Created persistent object 0:ffffa9fe63c9:f0d0:5900f9f9:0
Recreated object 0:ffffa9fe63c9:f0d0:5900f9f9:0
Final value: 4
Time taken: 13029 milliseconds

----

Recoverable testing shows for non-compiled:

Apr 26, 2017 8:52:12 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 61737 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Created persistent object 0:ffffa9fe63c9:f128:5900fa6c:0
Final value: 4
Time taken: 353 milliseconds

Compiler implementation:

Apr 26, 2017 8:54:52 PM <unknown> <unknown>
INFO: ARJUNA012170: TransactionStatusManager started on port 61784 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Created persistent object 0:ffffa9fe63c9:f157:5900fb0c:0
Final value: 4
Time taken: 222 milliseconds

----
