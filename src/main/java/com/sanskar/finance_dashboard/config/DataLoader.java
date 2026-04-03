package com.sanskar.finance_dashboard.config;

import com.sanskar.finance_dashboard.entity.Role;
import com.sanskar.finance_dashboard.entity.Transaction;
import com.sanskar.finance_dashboard.entity.Type;
import com.sanskar.finance_dashboard.entity.User;
import com.sanskar.finance_dashboard.repository.TransactionRepository;
import com.sanskar.finance_dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // Here we are inserting 50 sample users and 100 transactions of different type

    private final UserRepository userRepo;
    private final TransactionRepository transactionRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if (userRepo.count() > 0) return;

        Random random = new Random();


        for (int i = 1; i <= 50; i++) {

            User user = new User();
            user.setName("User" + i);
            user.setEmail("user" + i + "@gmail.com");
            user.setPassword(encoder.encode("1234"));


            if (i % 3 == 0) user.setRole(Role.ADMIN);
            else if (i % 3 == 1) user.setRole(Role.ANALYST);
            else user.setRole(Role.VIEWER);

            userRepo.save(user);
        }


        String[] categories = {"Food", "Rent", "Shopping", "Travel", "Salary"};
        Type[] types = {Type.INCOME, Type.EXPENSE};

        for (int i = 1; i <= 100; i++) {

            Transaction t = new Transaction();

            t.setAmount((double) (100 + random.nextInt(10000)));
            t.setType(types[random.nextInt(types.length)]);
            t.setCategory(categories[random.nextInt(categories.length)]);
            t.setDate(LocalDate.now().minusDays(random.nextInt(30)));
            t.setDescription("Sample transaction " + i);

            transactionRepo.save(t);
        }

        System.out.println("Sample data loaded successfully");
    }

}
