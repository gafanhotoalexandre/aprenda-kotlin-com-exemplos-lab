// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel {
    BASICO, INTERMEDIARIO, AVANCADO
}

data class ConteudoEducacional(
    val titulo: String,
    val descricao: String,
    val duracao: Int // minutos
) {
    override fun toString(): String {
        return "$titulo (${duracao}min) - $descricao"
    }
}

data class Aluno(val nome: String, val email: String) {
    private val formacoesMatriculadas = mutableListOf<Formacao>()

    fun addFormacao(formacao: Formacao) {
        if (formacoesMatriculadas.contains(formacao)) {
            println("Aluno $nome já está matriculado na formação ${formacao.nome}")
            return
        }

        formacoesMatriculadas.add(formacao)
        println("Aluno $nome matriculado na formação ${formacao.nome}")
    }

    fun listarFormacoesMatriculadas() {
        println("Formações matriculadas para $nome:")
        formacoesMatriculadas.forEach { println(" - ${it.nome} (${it.nivel})") }
    }
}

class Formacao(
    val nome: String,
    val nivel: Nivel,
    val conteudos: MutableList<ConteudoEducacional> = mutableListOf()
) {
    private val alunosMatriculados = mutableListOf<Aluno>()

    fun addConteudo(conteudo: ConteudoEducacional) {
        conteudos.add(conteudo)
    }

    fun matricular(aluno: Aluno) {
        if (alunosMatriculados.contains(aluno)) {
            println("Aluno já matriculado nesta formação.")
            return
        }

        alunosMatriculados.add(aluno)
        aluno.addFormacao(this)
    }

    fun calcularCargaHoraria(): Int {
        return conteudos.sumOf { it.duracao }
    }

    fun listarConteudos() {
        println("Conteúdos da formação $nome:")
        conteudos.forEachIndexed { index, conteudo ->
            println("${index + 1}. $conteudo")
        }
        println("Carga horária total: ${calcularCargaHoraria()} minutos.")
    }

    fun listarAlunosMatriculados() {
        println("Alunos matriculados em $nome:")
        alunosMatriculados.forEach { println(" - ${it.nome}") }
        println("Total de alunos: ${alunosMatriculados.size}")
    }
}

fun main() {
    // conteúdos
    val introducaoKotlin = ConteudoEducacional(
        "Introducao ao Kotlin",
        "Fundamentos da linguagem Kotlin",
        60
    )

    val pooKotlin = ConteudoEducacional(
        "POO com Kotlin",
        "Programação Orientada a Objetos utilizando Kotlin",
        120
    )

    val androidBasico = ConteudoEducacional(
        "Android Básico",
        "Desenvolvimento de aplicativos Android com Kotlin",
        180
    )

    val androidAvancado = ConteudoEducacional(
        "Android Avançado",
        "Técnicas avançadas para desenvolvimento Android",
        240
    )

    // formações
    val formacaoKotlin = Formacao("Formação Kotlin Developer", Nivel.INTERMEDIARIO)
    formacaoKotlin.addConteudo(introducaoKotlin)
    formacaoKotlin.addConteudo(pooKotlin)

    val formacaoAndroid = Formacao("Formação Android Developer", Nivel.AVANCADO)
    formacaoAndroid.addConteudo(introducaoKotlin)
    formacaoAndroid.addConteudo(androidBasico)
    formacaoAndroid.addConteudo(androidAvancado)

    // alunos
    val joao = Aluno("João da Silva", "joaos@email.com")
    val maria = Aluno("Maria Santos", "msantos@email.com")
    val gabriel = Aluno("Gabriel Oliveira", "goliveira@email.com")

    // matriculas
    formacaoKotlin.matricular(joao)
    formacaoKotlin.matricular(gabriel)

    formacaoAndroid.matricular(maria)
    formacaoAndroid.matricular(gabriel)

    // tentativa de "rematricular"
    formacaoKotlin.matricular(joao)

    println("\n===== INFORMAÇÕES DAS FORMAÇÕES =====")
    formacaoKotlin.listarConteudos()
    println()
    formacaoAndroid.listarConteudos()

    println("\n===== ALUNOS MATRICULADOS =====")
    formacaoKotlin.listarAlunosMatriculados()
    println()
    formacaoAndroid.listarAlunosMatriculados()

    println("\n===== FORMAÇÕES POR ALUNO =====")
    joao.listarFormacoesMatriculadas()
    println()
    maria.listarFormacoesMatriculadas()
    println()
    gabriel.listarFormacoesMatriculadas()
}