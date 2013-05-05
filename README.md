========================================================================================================================
README - Spring in Practice Recipe 13.2

Copyright (c) 2013 Manning Publications Co.

Willie Wheeler (willie.wheeler@gmail.com)

Book: http://manning.com/wheeler/
Blog: http://springinpractice.com/
Code: https://github.com/springinpractice
========================================================================================================================

Recipe 13.2 shows how to add self-service ticketing to the portal application. Now both apps need customer information,
and both need ticket information. While we could just do the same thing we did in recipe 13.1 (namely, have both apps
use both databases), this approach couples the database to the apps and over time makes it harder to make database
changes. So instead of doing that, instead we will implement bidirectional, point-to-point service integration via REST:
the portal app gets ticket categories from the help desk app and also posts new tickets to the help desk app, while the
help desk app gets customer data from the portal app. 
