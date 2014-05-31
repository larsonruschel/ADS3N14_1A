package structure;


//T - Valor Nodo || S - Chave Nodo
public class Arvore <T extends Comparable<T>, S extends Comparable<S>> {
	

	private Nodo<T, S> root;


	public Nodo<T, S> getRoot() {
		return root;
	}

	public void setRoot(Nodo<T, S> root) {
		this.root = root;
	}

	//Pega primeiro
	public Nodo<T, S> getFirst()
	{
		return getFirst(getRoot());
	}
	
	//Pega primeiro recursiva
	private Nodo<T, S> getFirst(Nodo<T, S> root)
	{
		if (root.getLeftNode() == null)
		{
			return root;
		}
		else
		{
			return getFirst(root.getLeftNode());
		}
	}
	
	// ultimo
	public Nodo<T, S> getLast()
	{
		return getLast(getRoot());
	}
	
	private Nodo<T, S> getLast(Nodo<T, S> root)
	{
		if (root.getRightNode() == null)
		{
			return root;
		}
		else
		{
			return getLast(root.getRightNode());
		}
	}
	
	// Add Nod0
	
	public void addNode(Nodo<T, S> node, boolean showCount)
	{
		addNode(node, getRoot(), 0, showCount);
	}
	
		private int addNode(Nodo<T, S> node, Nodo<T, S> root, int level, boolean showCount)
	{
		if (root == null)
		{
			level = 0;
			root = getRoot();
		}
		
		if (getRoot() == null)
		{
			setRoot(node);
			if (showCount)
			{
				System.out.println("======================================");
				System.out.println("O Nó foi adicionado no nível {" + level + "}.");
				System.out.println("A contagem total de Nós é de: " + countNodes());
				System.out.println("======================================");
			}
		}
		else
		{
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				level++;
				if (root.getRightNode() != null)
				{
					level = addNode(node, root.getRightNode(), level, showCount);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					if (showCount)
					{
						System.out.println("======================================");
						System.out.println("O Nó foi adicionado no nível {" + level + "}.");
						System.out.println("A contagem total de Nós é de: " + countNodes());
						System.out.println("======================================");
					}
				}
			}

			else 
			{
				level++;
				if (root.getLeftNode() != null)
				{
					level = addNode(node, root.getLeftNode(), level, showCount);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					if (showCount)
					{
						System.out.println("======================================");
						System.out.println("O Nó foi adicionado no nível {" + level + "}.");
						System.out.println("A contagem total de Nós é de: " + countNodes());
						System.out.println("======================================");
					}
				}
			}
		}
		return level;
	}
	
	// Deleta Nodo
	public void deleteNode(Nodo<T, S> node)
	{
		Nodo<T, S> root = node.getRoot();
		
		// Leaf
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{

			if (root.getLeftNode() != null && root.getLeftNode() == node)
			{
				root.setLeftNode(null);
			}
			else 
			{
				root.setRightNode(null);
			}
		}

		else if (node.getLeftNode() != null ^ node.getRightNode() != null)
		{

			if (node.getLeftNode() != null)
			{
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getLeftNode());
				}
				else 
				{
					root.setRightNode(node.getLeftNode());
				}
			}
			else
			{
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getRightNode());
				}
				else 
				{
					root.setRightNode(node.getRightNode());
				}
			}
		}

		else 
		{
			Nodo<T, S> rightNode = node.getRightNode();
			Nodo<T, S> leftNode = node.getLeftNode();
			
			if (root != null)
			{

				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(null);
				}
				else 
				{
					root.setRightNode(null);
				}
				
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
			else
			{
				setRoot(null);
				addNode(leftNode, false);
				addNode(rightNode, false);
			}
		}
	}
	
	// Conta Nodo
	public int countNodes()
	{
		return countNodes(1, getRoot());
	}
	
	private int countNodes(int count, Nodo<T, S> root)
	{

		if(root.getRightNode() != null)
		{
			count++;
			count = countNodes(count, root.getRightNode());
		}
		
		if (root.getLeftNode() != null)
		{
			count++;
			count = countNodes(count, root.getLeftNode());
		}
		
		return count;
	}

	// èsquisa Nodo
	public Nodo<T, S> searchNode(S key)
	{
		return searchNode(key, getRoot(), 1);
	}
	
	private Nodo<T, S> searchNode(S key, Nodo<T, S> root, int count)
	{
		// Verify root
		if (root.getKey().compareTo(key) == 0)
		{
			System.out.println("======================================");
			if (this instanceof AVL) System.out.print("AVL: ");
			else if (this instanceof RBT) System.out.print("RBT: ");
			System.out.println("Comparações feitas para achar o Nó: " + count);
			System.out.println("======================================");
			return root;
		}

		if (root.getKey().compareTo(key) > 0)
		{
			if (root.getLeftNode() != null)
			{
				count++;
				return searchNode(key, root.getLeftNode(), count);
			}
		}
		else
		{
			if (root.getRightNode() != null)
			{
				count++;
				return searchNode(key, root.getRightNode(), count);
			}
		}
		
		System.out.println("Comparações feitas: " + count);
		return null;
	}

//mostra a arvore
	public void showTree()
	{
		if (this instanceof AVL) System.out.print("AVL: ");
		if (this instanceof RBT)
		{
			System.out.println("*Nodos vermelhos serão mostrados com um hífen antes da chave*");
			System.out.print("RBT: ");
		}
		showTree(this.getRoot());
		System.out.println("");
		System.out.println("======================");
	}
	
	private void showTree(Nodo<T,S> node)
	{
		boolean flagLeft = node.getLeftNode() == null ? false : true;
		boolean flagRight = node.getRightNode() == null ? false : true;
		
		if (this instanceof RBT && !node.isBlack()) { System.out.print("-"); }
		System.out.print(node.getKey());
		
		if (flagLeft || flagRight)
		{
			System.out.print("(");
			

			if (flagLeft)
			{
				showTree(node.getLeftNode());
			}
			else 
			{
				System.out.print("[ ]");
			}
			
			System.out.print(", ");

			if (flagRight)
			{
				showTree(node.getRightNode());
			}
			else
			{
				System.out.print("[ ]");
			}
			
			System.out.print(")");
		}
		
	}

	protected void leftRotation(Nodo<T,S> node)
	{
		Nodo<T, S> R = node.getRightNode();
		Nodo<T, S> L = node.getRightNode().getLeftNode();
		Nodo<T, S> B = L;
		node.getRightNode().setLeftNode(node);
		node.setRightNode(B);

		R.setRoot(node.getRoot());
		if (node.getRoot() != null)
		{
			if(node.getRoot().getKey().compareTo(node.getKey()) <= 0)
			{
				node.getRoot().setRightNode(R);
			}
			else 
			{
				node.getRoot().setLeftNode(R);
			}
		}
		else
		{
			setRoot(R);
		}
		node.setRoot(R);
	}
	
	protected void rightRotation(Nodo<T, S> node)
	{
		Nodo<T, S> L = node.getLeftNode();
		Nodo<T, S> R = node.getLeftNode().getRightNode();
		Nodo<T, S> B = R;
		node.getLeftNode().setRightNode(node);
		node.setLeftNode(B);
		
		L.setRoot(node.getRoot());
		if (node.getRoot() != null)
		{
			if(node.getRoot().getKey().compareTo(node.getKey()) <= 0)
			{
				node.getRoot().setRightNode(L);
			}
			else 
			{
				node.getRoot().setLeftNode(L);
			}
		}
		else
		{
			setRoot(L);
		}
		node.setRoot(L);
	}

	protected void resetTree()
	{
		resetTree(this.getRoot());
	}
	
	private void resetTree(Nodo<T, S> node)
	{
		if (node != null)
		{			
			node.setBalance(99);
			node.setLevel(-1);
			
			resetTree(node.getLeftNode());
			resetTree(node.getRightNode());
		}
	}
	
	protected void showRootLevel()
	{
		showRootLevel(this.getRoot());
	}
	
	private void showRootLevel(Nodo<T, S> node)
	{

		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			node.setLevel(0);
			return;
		}

		if (node.getRightNode() != null && node.getRightNode().getLevel() == -1) showRootLevel(node.getRightNode());
		if (node.getLeftNode() != null && node.getLeftNode().getLevel() == -1) showRootLevel(node.getLeftNode());

		if (node.getLeftNode() == null) node.setLevel(node.getRightNode().getLevel() + 1);
		else if (node.getRightNode() == null) node.setLevel(node.getLeftNode().getLevel() + 1);
		else if (node.getLeftNode().getLevel() >= node.getRightNode().getLevel()) node.setLevel(node.getLeftNode().getLevel() + 1);
		else node.setLevel(node.getRightNode().getLevel() + 1);
		
		if (node.getRoot() == null && node.getLevel() != -1)
		{
			System.out.println("O nível do Root é " + node.getLevel());
			resetTree(node);
			return;
		}
	}
}
