package by.tms.lesson22.hw.calcualtor.service;

import by.tms.lesson22.hw.calcualtor.console.ConsoleWriter;
import by.tms.lesson22.hw.calcualtor.model.Operation;

public class OperationService {
    ConsoleWriter consoleWriter = new ConsoleWriter();
    public Operation calculate(Operation operation) {
        switch (operation.getOperation()) {
            case "SUM":
                operation.setResult(operation.getNum1() + operation.getNum2());
                return operation;
            case "SUB":
                operation.setResult(operation.getNum1() - operation.getNum2());
                return operation;
            case "MUL":
                operation.setResult(operation.getNum1() * operation.getNum2());
                return operation;
            case "SEP":

                operation.setResult(operation.getNum1() / operation.getNum2());

                return operation;
            default:
                consoleWriter.writer("invalid operation");
        }
        return operation;
    }
}

