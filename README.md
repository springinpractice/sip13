========================================================================================================================
README - Spring in Practice Recipe 13.3

Copyright (c) 2013 Manning Publications Co.

Willie Wheeler (willie.wheeler@gmail.com)

Book: http://manning.com/wheeler/
Blog: http://springinpractice.com/
Code: https://github.com/springinpractice
========================================================================================================================

From a user's perspective, the help desk and portal apps in recipe 13.3 are identical to those from recipe 13.2. Behind
the scenes, however, we pursue further decoupling between the apps. We remove the point-to-point REST calls, and replace
them instead with AMQP messaging backed by RabbitMQ. We use Spring Integration to translate between app-specific
representations and a canonical data model, as well as to provide support for AMQP-based communication.
