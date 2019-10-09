package com.drew.webnet.messenger.messenger.repository;

import com.drew.webnet.messenger.messenger.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository< Message,Long> {
}
