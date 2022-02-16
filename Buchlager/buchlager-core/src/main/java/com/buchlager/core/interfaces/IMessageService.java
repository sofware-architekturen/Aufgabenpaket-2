package com.buchlager.core.interfaces;

public interface IMessageService<T> {
    void sendMessage(T messageObject);
}
