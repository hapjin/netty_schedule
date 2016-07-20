# netty_schedule
This is a simple http server implemented by netty for submiting oozie jobs(such as mr job)
netty server accepts job request async and launch a request to oozie server.
As oozie supports Message Queue(such as ActiveMQ),You can config MQ to receive results.
