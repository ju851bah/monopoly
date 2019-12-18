package de.htwg.se.monopoly.util

import de.htwg.se.monopoly.model.playerComponent.IPlayer

class PlayerIterator(players: Array[IPlayer], startIdx : Int = 0) extends Iterator[IPlayer] {
    private var current = startIdx

    override def next(): IPlayer = {
        current += 1
        if (!hasNext)
            throw new NoSuchElementException
        players(current)
    }

    override def hasNext: Boolean = {
        if (current >= players.length)
            current = 0
        players(current) != null
    }

    def replace(player: IPlayer, newPlayer: IPlayer) = players(players.indexOf(player)) = newPlayer

    def list : List[IPlayer] = players.toList

    def currentIdx = current

    def copy = new PlayerIterator(players = players.clone(), startIdx = current)
}