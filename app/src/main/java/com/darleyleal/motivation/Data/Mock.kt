package com.darleyleal.motivation.Data

import com.darleyleal.motivation.Infrastructure.MotivationConstants
import kotlin.random.Random

class Mock {
    private val all = MotivationConstants.FILTER.ALL
    private val mood = MotivationConstants.FILTER.MOOD
    private val sun = MotivationConstants.FILTER.SUN
    private val phrasesList = listOf<Phrase>(
        Phrase(
            "Você não é derrotado quando perde, você é derrotado quando desiste!",
            mood
        ),
        Phrase("Quando está mais escuro, vemos mais estrelas!", mood),
        Phrase(
            "Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.",
            mood
        ),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", mood),
        Phrase(
            "O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?",
            mood
        ),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sun),
        Phrase("Você perde todas as chances que você não aproveita.", sun),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sun),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sun),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sun),
        Phrase("Se você acredita, faz toda a diferença.", sun),
        Phrase(
            "Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sun
        )
    )
}