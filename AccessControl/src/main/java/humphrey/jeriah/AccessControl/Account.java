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
    private double frozenBalance;
    private BankAccountType accountType= BankAccountType.CHECKING;
    private AccountStatus acstatus = AccountStatus.OPEN;
    private ApprovalStatus apstatus = ApprovalStatus.APPROVED;
    private OverdraftProtection op = OverdraftProtection.ENABLED;


    //constructor with unique identifier
    public Account(BankAccountType accountType, double amount, String name, AccountStatus status) {
        accountHolderName = name;
        balance = amount;
        accountNumberIncrement++;
        accountNumber = accountNumberIncrement;
        status = AccountStatus.OPEN;
        accountType = this.accountType;

    }

    public int getAccountNumber(){
        System.out.println(accountNumber);
        return accountNumber;
    }

    public String getAccountHolderName(){
        System.out.println("Name: " + accountHolderName);
        return accountHolderName;
    }

    public String setAccountHolderName( String name){
        if(acstatus.equals(AccountStatus.CLOSED)) {
            System.out.println("Closed Account. Cannot change name");
        }
        else{
         accountHolderName = name;
        }
        return accountHolderName;
    }

    private double credit(double amount) {
        balance += amount;
        System.out.println("Current Balance: " + balance);
        return balance;
    }

    private double debit(double amount) {

        balance -= amount;
        System.out.println("Current Balance: " + balance);
        return balance;
    }

    public double freezeAccountBalance() {
        frozenBalance = balance;
        balance = -1000000;
        return balance;
    }

    public double unfreezeAccountBalance() {
        balance = frozenBalance;
        return balance;
    }

    public double closeAccountBalance() {
        balance = 0;
        return balance;
    }

    public double getBalance() {
        if (acstatus.equals(AccountStatus.OFACFREEZE)) {
            System.out.println("Your balance is frozen");
            this.freezeAccountBalance();
        } else if (acstatus.equals(AccountStatus.CLOSED)) {
            this.closeAccountBalance();
        }
        System.out.println("Balance: " + balance);
        return balance;
    }


    public AccountStatus getAccountStatus() {

        System.out.println(acstatus);
        return acstatus;
    }


    public void updateBalanceCredit(double amount) {
        if (acstatus.equals(AccountStatus.OPEN)) {
            this.credit(amount);
            apstatus = ApprovalStatus.APPROVED;

        } else if (acstatus.equals(AccountStatus.CLOSED)) {
            System.out.println("Invalid Transaction. Account Closed");
            this.closeAccountBalance();
            apstatus = ApprovalStatus.NOT_APPROVED;
        } else if (acstatus.equals(AccountStatus.OFACFREEZE)) {
            this.freezeAccountBalance();
            System.out.println("Invalid Transaction. Account Frozen");
            apstatus = ApprovalStatus.ACCOUNT_FROZEN;

        }
    }

    public void updateBalanceDebit(double amount) {
        if (acstatus.equals(AccountStatus.OPEN)) {
            if (amount <= balance) {
                this.debit(amount);
                apstatus = ApprovalStatus.APPROVED;
            } else if (op.equals(OverdraftProtection.ENABLED)) {
                System.out.println("Transaction Rejected");
                apstatus = ApprovalStatus.NOT_APPROVED;

            } else if (op.equals(OverdraftProtection.DISABLED)) {
                this.debit(amount);
                apstatus = ApprovalStatus.APPROVED;
            }

        }

        else if(acstatus.equals(AccountStatus.CLOSED))

    {
        System.out.println("Invalid Transaction. Account Closed");
        this.closeAccountBalance();
        apstatus = ApprovalStatus.NOT_APPROVED;
    }
        else if(acstatus.equals(AccountStatus.OFACFREEZE))

    {
        this.freezeAccountBalance();
        System.out.println("Invalid Transaction. Account Frozen");
        apstatus = ApprovalStatus.ACCOUNT_FROZEN;

    }

}


    public AccountStatus setAccountStatus(AccountStatus status) {
        if (acstatus.equals(AccountStatus.CLOSED)) {
            System.out.println("This account is closed and can't be changed");
            status = acstatus;

        } else if (acstatus.equals(AccountStatus.OPEN)) {
            if (status.equals(AccountStatus.OFACFREEZE)) {
                System.out.println("This account has been frozen");
                acstatus = status;
            } else if (status.equals(AccountStatus.OPEN)) {
                System.out.println("This account is already open");
                acstatus = status;
            } else if (status.equals(AccountStatus.CLOSED)) {
                System.out.println("This account has been closed");
                balance = 0;
                acstatus = status;
            }

        } else if (acstatus.equals(AccountStatus.OFACFREEZE)) {
            if (status.equals(AccountStatus.OFACFREEZE)) {
                System.out.println("This account is already frozen");
                acstatus = status;
            } else if (status.equals(AccountStatus.OPEN)) {
                System.out.println("This account has been reopened");
                acstatus = status;
            } else if (status.equals(AccountStatus.CLOSED)) {
                System.out.println("This account has been closed");
                balance = 0;
                acstatus = status;
            }

        }
        return acstatus;
    }

    public OverdraftProtection setProtection(OverdraftProtection op){
            this.op = op;
            return op;
    }

    public double getInterestRate(){
        if (accountType.equals(BankAccountType.INVESTMENT)){
            interestRate = 5.0;
        }
        else{
            interestRate = 0.5;
        }
        return interestRate;
    }

    }





