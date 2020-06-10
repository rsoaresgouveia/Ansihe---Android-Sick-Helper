<?php

require("config.inc.php");

    $query = " 
            SELECT 
                nomeTitulo, 
                nomeAutor
            FROM titulo,autores 
            WHERE 
                nomeAutor = :pesquisa 
                AND titulo.idMetadado=autores.idMetadado
        ";
    
    $query_params = array(
        ':pesquisa' => $_POST['pesquisa']
    );

try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}

$rows = $stmt->fetchAll();


if ($rows) {
    $response["success"] = 1;
    $response["message"] = "Post Available!";
    $response["oas"]   = array();
    
    foreach ($rows as $row) {
        $post             = array();
        $post["titulo"]    = $row["titulo"];
        $post["autor"]  = $row["autor"];
        
        
        array_push($response["oas"], $post);
    }
    
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}

?>