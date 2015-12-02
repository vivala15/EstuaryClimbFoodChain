package SerializeTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import controller.Player;

public class PlayerSerialize {

	@Test
	public void serializePlayer() {

		try {
			Player p = new Player(null, null);

			FileOutputStream fos = new FileOutputStream("/tmp/player.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
			fos.close();
			System.out.println("Serialized player data saved in /tmp/player.ser");			

		} catch(IOException i) {
			i.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream("/tmp/player.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Player p = (Player) ois.readObject();
			ois.close();
			fis.close();

			assertEquals(p.getMouse(), null);
			assertEquals(p.getPlayerEntity(), null);
			
			//Clean up file
			new File("/tmp/player.ser").delete();
			
		} catch(Exception i) {
			fail("Exception throw during test: " + i.toString());
		}

	}
}
