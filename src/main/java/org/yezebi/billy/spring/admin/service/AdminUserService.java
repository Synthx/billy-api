package org.yezebi.billy.spring.admin.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yezebi.billy.spring.admin.dto.CreateUserDto;
import org.yezebi.billy.spring.user.dao.UserRepository;
import org.yezebi.billy.spring.user.entity.User;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final FirebaseAuth firebaseAuth;

    @Transactional
    public User create(CreateUserDto dto) {
        User user = null;
        final UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setDisplayName(dto.getNickname())
                .setEmailVerified(true);

        try {
            final UserRecord userRecord = firebaseAuth.createUser(request);

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userRecord.getUid());
            claims.put("role", dto.getRole().name());
            firebaseAuth.setCustomUserClaims(userRecord.getUid(), claims);

            user = User.builder()
                    .id(userRecord.getUid())
                    .email(dto.getEmail())
                    .nickname(dto.getNickname())
                    .role(dto.getRole())
                    .build();
            return this.userRepository.save(user);
        } catch (FirebaseAuthException e) {
            return null;
        } catch (DataException e) {
            if (user != null) {
                // todo: firebaseAuth.deleteUser(user.getId());
            }

            return null;
        }
    }
}
