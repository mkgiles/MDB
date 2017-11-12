package structures;

public class HashList<T> {
	private DataList<T> values;
	public HashList(int length) {
		values = new DataList<T>(length, null);
	}
	private static class FNV {
		private static final long prime = 16777619;
		private static final long offset = 2166136261L;
		private static int hash(byte[] data) {
			long hash = offset;
			for(byte unit : data) {
				hash ^= unit;
				hash *= prime;
			}
			hash = (hash>>32) ^ (hash & 0xffffffff);
			return (int) hash;
		}
	}
	public static int uid(String name, String year) {
		String key = name + year;
		return FNV.hash(key.getBytes());
	}
}
