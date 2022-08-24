package com.bn.market.repository;

import com.bn.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role getRoleByName(String name);
}
