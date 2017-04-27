# Notes

## REST APIs
Search Bracket - `/bracket/search?term=Foo` - GET - BracketLite Objects. Swaroop

Get Bracket    - `/bracket/id/123` - GET - Return the complete Bracket object. Swaroop

Create Bracket - `/bracket/id/123` - POST - Return the complete Bracket object. Vinay

Update Bracket - `/bracket/id/123` - PUT - Return the complete Bracket object. Vinay

Delete Bracket - `/bracket/id/123` - DELETE. Vinay

Update Match Outcome - `/match/123456/` - PUT - Return status only. 

Get Types of Brackets - `/bracket/types` - GET - Returns all the types from brackets (Ex. Single Elimination, ..)

Get Types of Sports - `/bracket/sports` - GET - Returns all the supported sports


## Build the Search

## Next Steps
### Swaroop
1. Tests
2. Travis & Cobertura
3. Deployment

### Vinay
1. REST APIs.
2. Integrate and PR.
3. PostgreSQL or MySQL Replication

### Nitesh
1. JHipster - Nitesh or Kollu

## Future
1. FTS
2. Eh Cache
3. Private Brackets
4. User Related Brackets
5. Obfuscation of URLs
