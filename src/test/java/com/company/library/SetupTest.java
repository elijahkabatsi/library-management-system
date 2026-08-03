package com.company.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SetupTest {

    @Test
    @DisplayName("Verify anti-pattern: return in finally swallows exception")
    void testReturnInFinallySwallowsException() {
        String result = AntiPatternDemo.returnInFinally();
        assertThat(result).isEqualTo("Everything is fine!");
    }

    @Test
    @DisplayName("Verify anti-pattern: log and continue leaves half-updated state")
    void testHalfUpdatedState() {
        AntiPatternDemo.Account account = new AntiPatternDemo.Account();
        account.updateAccount("Alice", 100.0);
        
        // Attempt bad update
        account.updateAccount("Bob", -50.0);

        // State is corrupted: name updated to 'Bob', but balance stayed at 100.0
        assertThat(account.getName()).isEqualTo("Bob");
        assertThat(account.getBalance()).isEqualTo(100.0);
    }
}