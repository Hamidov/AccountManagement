import com.bank.account.Account;
import com.bank.account.Operation;
import com.bank.account.OperationType;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Bank Account Test")
public class BankTest {

    private static Logger LOGGER = Logger.getLogger(Account.class);
    private static final Appender appender = mock(Appender.class);
    private final Account account = new Account("Nouri", "Ahmed", "10A4251B");

    @BeforeAll
    static void before() {
        LOGGER.addAppender(appender);
    }

    @AfterAll
    static void after() {
    }
    /**
     * user story 1 :
     * In order to save money
     * As a bank client
     * I want to make a deposit in my account
     */
    @Test
    @Order(1)
    @DisplayName("US 1")
    public void deposit() {
        Operation operation = new Operation(OperationType.DEPOSIT, new Date(), new BigDecimal(150));
        account.makeOperation(operation);
        assertEquals(new BigDecimal(150), account.getBalance());
    }

    /**
     * user story 2:
     * In order to retrieve some or all of my savings
     * As a bank client
     * I want to make a withdrawal from my account
     */
    @Test
    @Order(2)
    @DisplayName("US 2")
    public void withdrawal() {
        Operation deposit = new Operation(OperationType.DEPOSIT, new Date(), new BigDecimal(160));
        account.makeOperation(deposit);
        Operation withdrawal = new Operation(OperationType.WITHDRAWAL, new Date(), new BigDecimal(150));
        account.makeOperation(withdrawal);
        assertEquals(new BigDecimal(10), account.getBalance());
    }

    /**
     * US 3:
     * In order to check my operations
     * As a bank client
     * I want to see the history (operation, date, amount, balance) of my operations
     */
    @Test
    @Order(3)
    @DisplayName("US 3")
    public void showHistory() {
        Operation deposit = new Operation(OperationType.DEPOSIT, new Date(), new BigDecimal(600));
        account.makeOperation(deposit);
        Operation withdrawal = new Operation(OperationType.WITHDRAWAL, new Date(), new BigDecimal(200));
        account.makeOperation(withdrawal);
        account.showHistory();
    }

    @Test
    @Order(4)
    @DisplayName("US 4")
    public void depositWithNegativeAmount() {
        Operation deposit = new Operation(OperationType.DEPOSIT, new Date(), new BigDecimal(-150));
        assertThrows(IllegalArgumentException.class, () -> account.makeOperation(deposit));
    }

    @Test
    @Order(5)
    @DisplayName("US 5")
    public void withdrawalWithNegativeAmount() {
        Operation withdrawal = new Operation(OperationType.WITHDRAWAL, new Date(), new BigDecimal(-200));
        assertThrows(IllegalArgumentException.class, () -> account.makeOperation(withdrawal));
    }

    @Test
    @Order(6)
    @DisplayName("US 6 ")
    public void withdrawalMoreThanBalance() {
        Operation deposit = new Operation(OperationType.DEPOSIT, new Date(), new BigDecimal(150));
        account.makeOperation(deposit);
        Operation withdrawal = new Operation(OperationType.WITHDRAWAL, new Date(), new BigDecimal(160));
        account.makeOperation(withdrawal);
        ArgumentCaptor<LoggingEvent> argument = ArgumentCaptor.forClass(LoggingEvent.class);
        verify(appender).doAppend(argument.capture());
        assertEquals(Level.WARN, argument.getValue().getLevel());
        assertEquals("you don't have enough to make this withdrawal", argument.getValue().getMessage());
    }
}
