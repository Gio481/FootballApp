package com.example.footballapp.util.data_mapper

interface ModelMapper<in MODEL_A, out MODEL_B> : ModelListMapperIntoList<MODEL_A, MODEL_B> {
    fun modelMapper(model: MODEL_A): MODEL_B
    override fun modelListMapper(model: List<MODEL_A>): List<MODEL_B> {
        return model.map { modelMapper(it) }
    }
}