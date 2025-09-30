package com.br.checklist.checklist.repositories;

import com.br.checklist.checklist.enums.RoleEnum;
import com.br.checklist.checklist.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleEnum roleName);
}
