package com.example.footballapp.util.data_mapper

interface ModelListMapperIntoList<in MODEL_A, out MODEL_B> {
    fun modelListMapper(model: List<MODEL_A>): List<MODEL_B>
}