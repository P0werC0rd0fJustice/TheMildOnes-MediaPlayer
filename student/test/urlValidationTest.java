import org.junit.Test;
import static org.junit.Assert.*;
public class urlValidationTest{
	
	@Test
	public void givenValidURL_whenValidCheck_thenReturnTrue(){
		String u1 = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
		String u2 = "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8";
		String u3 = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8";
		boolean v1 = urlValidator.validURL(u1);
		boolean v2 = urlValidator.validURL(u2);
		boolean v3 = urlValidator.validURL(u3);
		assertTrue(v1);
		assertTrue(v2);
		assertTrue(v3);
	}

	public void givenInvalidURL_whenValidCheck_thenReturnFalse(){
		String u1 = "gooogle.com";
		String u2 = "http://reddit.com/r/programming";
		String u3 = "https://youtube.co.uk/stream.m3uu8";
		boolean v1 = urlValidator.validURL(u1);
		boolean v2 = urlValidator.validURL(u2);
		boolean v3 = urlValidator.validURL(u3);
		assertFalse(v1);
		assertFalse(v2);
		assertFalse(v3);
	}
}
