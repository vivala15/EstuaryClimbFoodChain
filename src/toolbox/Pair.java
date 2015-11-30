package toolbox;

public class Pair<L,R> {
	


	L l;
	R r;
	
	public Pair(L l, R r){
		this.l = l;
		this.r = r;
	}


	
	public L getL() {
		return l;
	}

	public void setL(L l) {
		this.l = l;
	}

	public R getR() {
		return r;
	}

	public void setR(R r) {
		this.r = r;
	}
}