import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import { DynamoDBDocumentClient, ScanCommand, PutCommand, GetCommand } from "@aws-sdk/lib-dynamodb";

const client = new DynamoDBClient({});
const docClient = DynamoDBDocumentClient.from(client);

export const handler = async (event) => {
    let body;
    let statusCode = 200;
    const headers = { "Content-Type": "application/json" };
    
    const path = event.rawPath || event.path || "";
    const method = event.requestContext?.http?.method || "GET";

    try {
        if (path.includes("/login") && method === "POST") {
            const loginData = JSON.parse(event.body);
            const res = await docClient.send(new GetCommand({
                TableName: "Usuarios",
                Key: { email: loginData.email }
            }));
            
            if (!res.Item || res.Item.password !== loginData.password) {
                statusCode = 401;
                body = { message: "Credenciales inválidas" };
            } else {
                body = res.Item;
            }
        } 
        else if (path.includes("/productos") && method === "GET") {
            const res = await docClient.send(new ScanCommand({ TableName: "Productos" }));
            body = res.Items;
        }
        else if (path.includes("/usuarios") && method === "POST") {
            const newUser = JSON.parse(event.body);
            await docClient.send(new PutCommand({
                TableName: "Usuarios",
                Item: newUser
            }));
            body = { message: "Usuario creado con éxito" };
        }
        
        else if (path.includes("/usuarios") && method === "PUT") {
            const updatedUser = JSON.parse(event.body);
            
            await docClient.send(new PutCommand({
                TableName: "Usuarios",
                Item: updatedUser 
            }));
            body = { message: "Usuario actualizado con éxito", user: updatedUser };
        }

        else {
            body = { message: "Ruta no encontrada", path, method };
        }
    } catch (err) {
        statusCode = 500;
        body = { error: err.message };
    }

    return {
        statusCode,
        body: JSON.stringify(body),
        headers
    };
};