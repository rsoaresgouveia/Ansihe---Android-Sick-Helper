<?php

require("config.inc.php");

    $query = " 
            SELECT 
                categoria
            FROM usuario 
            WHERE 
                :cpf = cpf
        ";
    
    $query_params = array(
        ':cpf' => $_POST['cpf']
    );

try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Erro na 'resposta do banco de dados";
    die(json_encode($response));
}

$rows = $stmt->fetchAll();
if ($rows) {
    $response["success"] = 1;
    $response["resultado"]   = array();
    
    foreach ($rows as $row) {
        $post = array();
        $post["categoria"] = $row["categoria"];

        
        array_push($response["resultado"], $post);
    }
    
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}

?>