Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation succesful with correct username and password
        Given command new user is selected
        When  username "pekkaton" and password "akkepa112" are entered
        Then  system will respond with "new user registered"

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  username "pekkaton" and password "ak" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with correct username and password consisting of letters
        Given command new user is selected
        When  username "pekkaton" and password "akakakakakakakak" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid passord
        Given command new user is selected
        When  username "p" and password "akkepa112" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with already taken username and valid pasword
        Given command new user is selected
        When  username "pekka" and password "akkepa112" are entered
        Then  system will respond with "new user not registered"
    
    Scenario: can login with succesfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" and password "salainen1" are entered
       Then  system will respond with "logged in"

    Scenario: can not login with account that is not succesfully created
        Given user "aa" with password "aa" is created
        And   command login is selected
        When  username "aa" and password "aa" are entered
        Then  system will respond with "wrong username or password" 