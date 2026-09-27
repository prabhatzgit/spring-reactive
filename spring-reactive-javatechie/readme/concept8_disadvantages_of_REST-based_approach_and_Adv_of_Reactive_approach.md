DAO class acts as a Publisher and postman or browser acts as a Subscriber.

http:localhost:8080/customers

when it calls to getCustomers of CustomerService.

Here in this method call, manually sleep of execution thread occurs.

processing count : 1
processing count : 2
processing count : 3
processing count : 4
processing count : 5
processing count : 6
processing count : 7
processing count : 8
processing count : 9
processing count : 10
Total execution time : 10113

Once all the threads executed, then only we receive the response.

In Spring reactive approach each thread object fetch the response immediately and not wait on others
to execute and fetch the response.

So, thats why reactive approach is asynchronous and non-blocking.

Total execution time : 2
processing count in stream flow : 1
processing count in stream flow : 2
processing count in stream flow : 3
processing count in stream flow : 4
processing count in stream flow : 5
processing count in stream flow : 6
processing count in stream flow : 7
processing count in stream flow : 8
processing count in stream flow : 9
processing count in stream flow : 10

In Reactive approach, whenever the subscriber cancel the request, immediately threads stop execution at backend.
But, in traditional approach if subscriber cancel the request, still threads performs execution at backend.

