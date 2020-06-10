<?php

//load and connect to MySQL database stuff
require("config.inc.php");

//initial query
$query = "
        INSERT INTO usuario ( cpf, nome, doenca, sala, categoria, cpfResponsavel) 
        VALUES ( :cpf, :nome, :doenca, :sala, :categoria, :cpfResponsavel) 
        ";

//Update query
$query_params = array(
    ':cpf' => $_POST['cpf'],
    ':nome' => $_POST['nome'],
    ':doenca' => $_POST['doenca'],
    ':sala' => $_POST['sala'],
    ':categoria' => $_POST['categoria'],
    ':cpfResponsavel' => $_POST['cpfResponsavel'],
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
   