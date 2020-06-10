<?php

//load and connect to MySQL database stuff
require("config.inc.php");

//initial query
$query = "
        INSERT INTO mensagens ( Usuario_cpf, mensagens, dataEnvio, categoria, destinatario ) 
        VALUES ( :Usuario_cpf, :mensagens, :dataEnvio, :categoria, :destinatario ) 
        ";

//Update query
$query_params = array(
    ':Usuario_cpf' => $_POST[':Usuario_cpf'],
    ':mensagens' => $_POST['mensagens'],
    ':dataEnvio' => $_POST['dataEnvio'],
    ':categoria' => $_POST['categoria'],
    ':destinatario' => $_POST['destinatario'],
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
$response["message"] = "Comentário adicionado com sucesso";
echo json_encode($response);
   