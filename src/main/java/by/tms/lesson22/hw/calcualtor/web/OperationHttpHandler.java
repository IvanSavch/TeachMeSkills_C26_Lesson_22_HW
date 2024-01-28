package by.tms.lesson22.hw.calcualtor.web;

import by.tms.lesson22.hw.calcualtor.model.Operation;
import by.tms.lesson22.hw.calcualtor.service.OperationService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class OperationHttpHandler implements HttpHandler {
    private final OperationService operationService = new OperationService();
    private final Map<String,String> mapResult = new HashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery(); //localhost:8080/calc?num1=2&num2=2&type=sum
        String[] parameters = query.split("&");
        double num1 = 0;
        double num2 = 0;
        String type = null;
        for (String string:parameters) {
            mapResult.put(string.substring(0,4),string.substring(5));
        }
        for (Map.Entry<String,String> map:mapResult.entrySet()){
            if (map.getKey().equals("num1")) {
                 num1 = Double.parseDouble(map.getValue());
            } else if (map.getKey().equals("num2")) {
                 num2 = Double.parseDouble(map.getValue());
            }else {
                 type = map.getValue();
            }
        }
        Operation operation = new Operation(num1,num2,type);
        operationService.calculate(operation);
        String result ="result = " + operation.getResult();

        exchange.sendResponseHeaders(200, result.length());

        PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
        printWriter.print(result); //Result = 4
        printWriter.flush();
    }
}
