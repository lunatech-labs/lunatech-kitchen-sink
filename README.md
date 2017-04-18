Lunatech Kitchen Sink
=================================

This is a simple seed template to create new Play Framework projects with Google login. Furthermore you can define admins with extra privileges for your application.

Required configuration in `conf/application.conf`

```
google {
    clientID = ???
    secret = ???
}

administrators = [
    "developer@lunatech.com",
    "admin@lunatech.com"
]
```
