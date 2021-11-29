package org.gvozdev.tetrokot.data

class ScoreComparator : Comparator<Player> {
    override fun compare(o1: Player, o2: Player): Int {
        if (o1.score == null || o2.score == null) {
            return 0
        }
        return -o1.score.compareTo(o2.score)
    }
}