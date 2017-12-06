package com.samples.rae.srv;

import com.samples.rae.srv.domain.Account;
import io.reactivex.Observable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Observable<Account> getAccounts(String username) {
        return Observable.range(0,9)
                .map(integer -> new Account("Account-"+integer, 150.35f));
    }

}
