<?php

//load and connect to MySQL database stuff
require("config.inc.php");

//initial query
$query = "
        INSERT INTO usuario ( cpf, nome, especialidade, categoria) 
        VALUES ( :cpf, :nome, :especialidade, :categoria) 
        ";

//Update query
$query_params = array(
    ':cpf' => $_POST['cpf'],
    ':nome' => $_POST['nome'],
    ':especialidade' => $_POST['especialidade'],
    ':categoria' => $_POST['categoria']
);
  
//execute query
try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    // For testing, you could use a die and message. 
    //die("Failed to run query: " . $ex->getMessage());
        
    //or just use this use this one:
    $response["success"] = 0;
    $response["message"] = "Post Inválido";
    die(json_encode($response));
}

$response["success"] = 1;
echo json_encode($response);
   