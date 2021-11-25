package fr.uge.bank;

import java.util.HashMap;

public class AccountManager {
    
    private HashMap<Integer, Long> accounts = new HashMap<>();
    
    
    public AccountManager() {
        accounts.put(1, 100_000_000L);
        accounts.put(2, 10L);
        accounts.put(3, 2_663_884L);
        accounts.put(4, 10_000L);
    }

    public boolean withdraw(int amount, int id) {
        Long balance = accounts.get(id);
        if (balance-amount < 0 || balance == null)
            return false;
        balance -= amount;
    	accounts.put(id, balance);
        return true;
        
    }
    
    public void deposit(int amount, int id) {
    	Long oldAmount = accounts.get(id);
    	oldAmount += amount;
    	accounts.put(id, oldAmount);
    }
    
    public long amount(int id) {
        return accounts.get(id);
    }
}
