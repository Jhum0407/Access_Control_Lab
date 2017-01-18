package humphrey.jeriah.AccessControl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeriahhumphrey on 1/18/17.
 */
public class AccountTest {
    Account account;

    @Before
    public void setUp() {
        account = new Account(BankAccountType.CHECKING, 5000.00, "John Smith", AccountStatus.OPEN);
    }

    @Test
    public void getAccountNumberTest(){
        int expected= 10001;
        int actual = account.getAccountNumber();
        assertEquals("I expect account number to read 10001", expected, actual);
    }

    @Test
    public void getAccountNameTest(){
        String expected = "John Smith";
        String actual = account.getAccountHolderName();
        assertEquals("I expect the name to change to Mike Jones", expected, actual);
    }

    @Test
    public void setAccountNameTestOpen(){
        String expected = "Mike Jones";
        String actual = account.setAccountHolderName("Mike Jones");
        assertEquals("I expect the name to change to Mike Jones", expected, actual);
    }

    @Test
    public void setAccountNameTestClosed(){
        account.setAccountStatus(AccountStatus.CLOSED);
        String expected = "John Smith";
        String actual = account.setAccountHolderName("Mike Jones");
        assertEquals("I expect the name not to change", expected, actual);
    }

    @Test
    public void getBalanceTestOpen() {
        double expected = 5000.00;
        double actual = account.getBalance();
        assertEquals("I expect getBalance to display 5000", expected, actual, 0.0);
    }

    @Test
    public void getBalanceTestFrozen() {
        account.setAccountStatus(AccountStatus.OFACFREEZE);
        double expected = -1000000;
        double actual = account.getBalance();
        assertEquals("I expect getBalance to display 0", expected, actual, 0.0);
    }
    @Test
    public void getBalanceTestClosed() {
        account.setAccountStatus(AccountStatus.CLOSED);
        double expected = 0;
        double actual = account.getBalance();
        assertEquals("I expect getBalance to display 0", expected, actual, 0.0);
    }




    @Test
    public void getAccountStatusTest()
    {

        AccountStatus expected = AccountStatus.OPEN;
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.Open", expected, actual);
    }




    @Test
    public void setAccountStatusOpenToFrozenTest()
    {

        AccountStatus expected = AccountStatus.OFACFREEZE;
        account.setAccountStatus(AccountStatus.OFACFREEZE);
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.OFACFREEZE", expected, actual);
    }

    @Test
    public void setAccountStatusOpentoClosedTest()
    {

        AccountStatus expected = AccountStatus.CLOSED;
        account.setAccountStatus(AccountStatus.CLOSED);
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.Closed", expected, actual);
    }

    @Test
    public void setAccountStatusFrozentoClosedTest()
    {

        account.setAccountStatus(AccountStatus.OFACFREEZE);
        AccountStatus expected = AccountStatus.CLOSED;
        account.setAccountStatus(AccountStatus.CLOSED);
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.Closed", expected, actual);
    }

    @Test
    public void setAccountStatusFrozentoOpenTest()
    {

        account.setAccountStatus(AccountStatus.OFACFREEZE);
        AccountStatus expected = AccountStatus.OPEN;
        account.setAccountStatus(AccountStatus.OPEN);
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.Closed", expected, actual);
    }


    @Test
    public void setAccountStatusClosedCannotChangeTest()
    {

        account.setAccountStatus(AccountStatus.CLOSED);
        AccountStatus expected = AccountStatus.CLOSED;
        account.setAccountStatus(AccountStatus.OPEN);
        AccountStatus actual = account.getAccountStatus();
        assertEquals("I expect AccountStatus.Closed", expected, actual);
    }


    @Test
    public void updateAccountBalanceStatusTestOpenAccountCredit()
    {
        account.getBalance();
        double expected = 6000;
        account.updateBalanceCredit(1000);
        double actual = account.getBalance();
        assertEquals("I expect balance to update to 6000", expected, actual, 0.0);
    }

    @Test
    public void updateAccountBalanceStatusTestFrozenAccountCredit()
    {
        account.setAccountStatus(AccountStatus.OFACFREEZE);
        account.getBalance();
        double expected = -1000000;
        account.updateBalanceCredit(1000);
        double actual = account.getBalance();
        assertEquals("I expect balance to be Frozen and display -1000000", expected, actual, 0.0);
    }


    @Test
    public void updateAccountBalanceStatusTestClosedAccountCredit()
    {
        account.setAccountStatus(AccountStatus.CLOSED);
        double expected=0;
        account.updateBalanceCredit(1000);
        double actual = account.getBalance();
        assertEquals("I expect balance to update to 0", expected, actual, 0.0);
    }

    @Test
    public void debitTestOpenAccountNoOverdraftNeeded(){
        {
            account.getBalance();
            double expected = 4000;
            account.updateBalanceDebit(1000);
            double actual = account.getBalance();
            assertEquals("I expect balance to update to 4000", expected, actual, 0.0);
        }


    }
    @Test

    public void setOverdraftProtection(){
        OverdraftProtection expected = OverdraftProtection.DISABLED;
        OverdraftProtection actual = account.setProtection(OverdraftProtection.DISABLED);
        assertEquals("I expect Overdraft Protection to switch to disabled", expected, actual);
    }

    @Test
    public void debitTestOpenOverdraftProtection()
        {  account.setProtection(OverdraftProtection.ENABLED);
            account.getBalance();
            double expected = 5000;
            account.updateBalanceDebit(10000);
            double actual = account.getBalance();
            assertEquals("I expect balance to update to 5000", expected, actual, 0.0);
        }

        @Test
        public void debitTestOpenOverdraftProtectionDisabled()
            {  account.setProtection(OverdraftProtection.DISABLED);
                account.getBalance();
                double expected = -5000;
                account.updateBalanceDebit(10000);
                double actual = account.getBalance();
                assertEquals("I expect balance to update to 5000", expected, actual, 0.0);
            }


            @Test
            public void updateAccountBalanceStatusTestClosedAccountDebit()
            {
                account.setAccountStatus(AccountStatus.CLOSED);
                double expected=0;
                account.updateBalanceDebit(1000);
                double actual = account.getBalance();
                assertEquals("I expect balance to update to 0", expected, actual, 0.0);
            }

    @Test
    public void updateAccountBalanceStatusTestFrozenAccountDebit()
    {
        account.setAccountStatus(AccountStatus.OFACFREEZE);
        double expected=-1000000;
        account.updateBalanceDebit(1000);
        double actual = account.getBalance();
        assertEquals("I expect balance to update to 0", expected, actual, 0.0);
    }

    @Test

    public void getInterestRateInvestment(){



        double expected = 0.5;
        double actual = account.getInterestRate();
        assertEquals("I expect an interest rate of 5.0", expected, actual, 0.0);
    }




}




