package edu.curtin.madcity;

public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException()
    {
        super("Insufficient funds");
    }
}
