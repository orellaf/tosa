package fr.lta.tosa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NodeTest {
	
	class Node {
		Node left,right;
		public int v;
		
		
		
		public Node(Node left, Node right, int v) {
			super();
			this.left = left;
			this.right = right;
			this.v = v;
		}

		public Node find(int v) {
			return this.find(this, v);
		}
		
		private Node find(Node  n , int v ) {
			if(n.v == v) return n;
			
			if(n.left != null && n.v > v) return find(n.left, v);
			
			if(n.right != null && n.v < v) return find(n.right, v);
			
			return null;
		}
		
	}
	
	private Node buildRootNode( ) {
		return new Node(
					new Node(
						new Node(
								new Node(null, null,2),
								new Node(null,null,6),
								5),
						new Node(null,null,8),
						7
						),
					new Node(
							null,
							new Node(
									new Node(null,null,16),
									null,
									17
									),
							13
							),
				9);
	}

	@Test
	@DisplayName("find 8 , OK")
	void test00() {
		var n = buildRootNode();
		
		var find = n.find(8);
		assertNotNull(find);
		assertEquals(8, find.v);
	}
	
	@Test
	@DisplayName("find 16 , OK")
	void test01() {
		var n = buildRootNode();
		
		var find = n.find(16);
		assertNotNull(find);
		assertEquals(16, find.v);
	}
	
	@Test
	@DisplayName("find 0 , KO")
	void test02() {
		var n = buildRootNode();
		
		var find = n.find(0);
		assertNull(find);
	}

}
