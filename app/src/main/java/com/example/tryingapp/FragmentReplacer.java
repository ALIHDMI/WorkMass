package com.example.tryingapp;

public interface FragmentReplacer {
    void replace(int position, BaseFragment newFragment, Boolean isNotify, Boolean isYes);
    BaseFragment replaceDef(int position, Boolean isNotify, Boolean isYes);
}
