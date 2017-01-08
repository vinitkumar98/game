1) This Connect4 game API service takes json object as input and sends a json response back to client. 
2) Input json string basically represent the connect4 grid at client request.
3) Output json string represents the status of running game along with the information like - clientId, last-dice position and
	player name (say - A, B etc), dice-colour and game-outcome.
4) Connect4 game rule is enforced within code by connecting 4 consecutive chars(colours) horizontally, vertically 
	and diagonally. Other boundary conditions like full column is also enforced.
5) JUnit test case has been written for wining and Running scenario.
6) Project can be build using maven (.pom file has been given) and deployed into any local or EC2 managed container service.
7) Connect4 Game service exposed as REST using jersey client.
8) A web.xml is also supplied to the project for deploying into a container. 
