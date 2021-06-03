fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var result = 0
    for (i in 0..strings.size - 1) {
        if (str == strings[i]) result++
    }
    return result
}