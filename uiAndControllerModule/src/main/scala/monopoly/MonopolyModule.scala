package monopoly;

import com.google.inject.AbstractModule
import monopoly.controller.IController
import monopoly.controller.controllerBaseImpl.Controller
import monopoly.util.fileIo.IFileIo
import monopoly.util.fileIo.fileIoXml.FileIoXml
import net.codingwell.scalaguice.ScalaModule
import playerModule.fieldComponent.fieldBaseImpl.ActionField
import playerModule.playerComponent.IPlayer
import playerModule.playerComponent.playerBaseImpl.Player
import playerModule.util.FieldIterator

class MonopolyModule extends AbstractModule with ScalaModule {


    def configure(): Unit = {
        bind[IController].to[Controller]
        //bind[IController].to[MockController]

        bind[IPlayer].toInstance(Player("", 0, ActionField(""), Set(), FieldIterator(List())))
        //bind[IBoard].toInstance(Board(List(), null, PlayerIterator(Array())))
        bind[IFileIo].to[FileIoXml]
        //bind[IFileIo].to[FileIoJson]
    }

}