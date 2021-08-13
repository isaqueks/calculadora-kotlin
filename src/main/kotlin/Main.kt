import kotlin.math.pow

const val OP_SUM = 1
const val OP_SUB = 2
const val OP_MUL = 3
const val OP_DIV = 4
const val OP_POW = 5
const val OP_MOD = 6

fun doSum(a: Float, b: Float): Float = a + b
fun doSub(a: Float, b: Float): Float = a - b
fun doMul(a: Float, b: Float): Float = a * b
fun doDiv(a: Float, b: Float): Float = a / b
fun doPow(a: Float, b: Float): Float = a.pow(b)
fun doMod(a: Float, b: Float): Float = a % b

val operationMap: Map<Int, (Float, Float) -> Float> = mapOf(
    OP_SUM to ::doSum,
    OP_SUB to ::doSub,
    OP_MUL to ::doMul,
    OP_DIV to ::doDiv,
    OP_POW to ::doPow,
    OP_MOD to ::doMod
)

fun askFloat(
    promptMessage: String,
    failMessage: String,
    validateFn: (Float) -> Boolean): Float {

    print(promptMessage)
    var floatValue: Float? = null;
    while (floatValue == null) {
        val input = readLine()
        floatValue = input?.toFloatOrNull()
        if (floatValue == null || !validateFn(floatValue!!)) {
            floatValue = null;
            println(failMessage)
        }
    }
    println()
    return floatValue!!
}

fun main(args: Array<String>) {
    while (true) {

        val operation = askFloat("Digite a operação: $OP_SUM para soma, $OP_SUB para subtração, " +
                "$OP_MUL para multiplicação, $OP_DIV para divisão, " +
                "$OP_POW para potenciação, $OP_MOD para resto: ",
        "Operação inválida."){op: Float ->
            op.toInt().toFloat() == op && op >= OP_SUM && op <= OP_MOD
        }

        val validateNumber: (Float) -> Boolean = {num: Float ->
            num >= Float.MIN_VALUE && num <= Float.MAX_VALUE
        }

        val num1 = askFloat("Número 1: ", "Número inválido!", validateNumber)
        val num2 = askFloat("Número 2: ", "Número inválido", validateNumber)

        val calculateFn: ((Float, Float) -> Float) = operationMap[operation.toInt()]!!
        val result = calculateFn(num1, num2)

        println("O resultado é: $result")

    }
}