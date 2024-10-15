package com.talent_acquisition_system.repository;

import com.talent_acquisition_system.model.Hr;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HrRepository extends MongoRepository<Hr, String> {
    Hr findByEmailAndPassword(String email, String password);

    Hr findByEmail(String email);
}
