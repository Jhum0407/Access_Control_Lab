package humphrey.jeriah.AccessControl;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by jeriahhumphrey on 1/17/17.
 */
public class Account {


    private static int accountNumberIncrement = 10000;
    private int accountNumber;
    private double balance;
    private double interestRate;
    private String accountHolderName;
    private BankAccountType accountType;
    private AccountStatus status;
    private ApprovalStatus astatus;
    private OverdraftProtection op;


    //constructor with unique identifier
    public Account( BankAccountType accountType, double amount, String name, AccountStatus status) {
        accountHolderName=name;
        balance=amount;
        accountNumberIncrement++;
        accountNumber = accountNumberIncrement;
        status= this.status;
        accountType= this.accountType;

    }

    private double credit(double amount){
        balance +=amount;
        System.out.println("Current Balance: " + balance);
        return balance;
    }

    private double debit( double amount){

        balance -= amount;
        System.out.println("Current Balance: " + balance);
        return balance;
    }


    public void updateBalance(double amount) {
        if(status.equals(status.OPEN)){
            if(amount>0){
                this.credit(amount);
                astatus= ApprovalStatus.APPROVED;

            }
            else if (amount<0){
                if (op.equals(op.ENABLED)){
                    System.out.println("Transaction rejected");
                    astatus= ApprovalStatus.NOT_APPROVED;
                }
            }
        }
    }

    }

