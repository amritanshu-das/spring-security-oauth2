Sample Spring Security application demonstrating setting up of OAUTH 2.0 Authorization server.

Here the 'Authorization' server has being customized to do below things:
    1. Use a custom table in MSSQL to verify the user.
    2. Use custom encoder (BCRYPT) to match the user password instead of default encoder ({noop}test123 or {bcrypt}$2a$10$MF7hYnWLeLT66gNccBgxaONZHbrSMjlUofkp50sSpBw2PJjUqU.zS)

For setting up 'Resource' server use - https://github.com/amritanshu-das/spring-boot-rest

#Spring Security #Spring Boot #JPA #MSSQL #OAUTH2

SQL [Clear text password - test123]:

USE [spring]
GO

INSERT INTO [dbo].[win_user]
           ([id]
           ,[login]
           ,[password])
     VALUES
           (1
           ,'adas'
           ,'$2a$12$AYuUCCAkvDMBQNFthOtlMO6VkrM785MGoBEA6UWYzX.bNxNy28Ic.')
GO