package com.morben.bank.ws.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="users")
public class UserEntity {

        private static final long serialVersionUID = -7344129444912928289L;

        @Id
        @GeneratedValue
        private Long id;

        @Column(length=30, nullable=false)
        private String userId;

        @Column(length = 200)
        private String nome;

        @Column(nullable = false, length = 200, unique=true)
        private String email;

        @Column(nullable = false)
        private String encryptedPassword;

        private String emailVerificationToken;

        @Column(nullable = false)
        private Boolean emailVerificationStatus = false;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getEncryptedPassword() {
                return encryptedPassword;
        }

        public void setEncryptedPassword(String encryptedPassword) {
                this.encryptedPassword = encryptedPassword;
        }

        public String getEmailVerificationToken() {
                return emailVerificationToken;
        }

        public void setEmailVerificationToken(String emailVerificationToken) {
                this.emailVerificationToken = emailVerificationToken;
        }

        public Boolean getEmailVerificationStatus() {
                return emailVerificationStatus;
        }

        public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
                this.emailVerificationStatus = emailVerificationStatus;
        }
}
