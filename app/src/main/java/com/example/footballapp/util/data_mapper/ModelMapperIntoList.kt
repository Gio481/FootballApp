package com.example.footballapp.util.data_mapper

interface ModelMapperIntoList<in MODEL_A, out MODEL_B> {
    fun modelMapperIntoList(model: MODEL_A): List<MODEL_B>
}