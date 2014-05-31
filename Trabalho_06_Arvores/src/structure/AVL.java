package structure;


// T - Valor Nodo || S - Chave Nodo
public class AVL<T extends Comparable<T>, S extends Comparable<S>> extends Arvore<T, S> {
	private int comp;
	private int rot;
	public int compTotal = 0;
	public int rotTotal = 0;
	
	public void addNodeAVL(Nodo<T, S> node, boolean showCount)
	{
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		addNodeAVL(node, getRoot(), showCount);
	}
	
	private void addNodeAVL(Nodo<T, S> node, Nodo<T, S> root, boolean showCount)
	{
		if (root == null)
		{
			root = getRoot();
		}
		
		if (getRoot() == null)
		{
			setRoot(node);
			if (showCount)
			{
				// mostra arvore
				super.showTree();
				return;
			}
		}
		else
		{

			comp++;
			if(root.getKey().compareTo(node.getKey()) <= 0)
			{
				if (root.getRightNode() != null)
				{
					addNodeAVL(node, root.getRightNode(), showCount);
				}
				else
				{
					node.setRoot(root);
					root.setRightNode(node);
					resetTree();
					balanceTree(showCount);
					if (showCount)
					{
						
						this.showRotComp();
						super.showTree();
						super.showRootLevel();
					}
				}
			}
		
			else 
			{
				if (root.getLeftNode() != null)
				{
					addNodeAVL(node, root.getLeftNode(), showCount);
				}
				else
				{
					node.setRoot(root);
					root.setLeftNode(node);
					resetTree();
					balanceTree(showCount);
					if (showCount)
					{
					
						super.showRootLevel();
						this.showRotComp();
						super.showTree();						
					}
				}
			}
		}
	}

	// Deleta Nodo
	@Override
	public void deleteNode(Nodo<T, S> node)
	{		
		compTotal += comp;
		rotTotal += rot;
		comp = 0;
		rot = 0;
		
		Nodo<T, S> root = node.getRoot();
		
		comp++;
		if (node.getLeftNode() == null && node.getRightNode() == null)
		{

			comp++;
			if (root.getLeftNode() != null && root.getLeftNode() == node)
			{
				root.setLeftNode(null);
				balanceTree(true);
			}
			else 
			{
				root.setRightNode(null);
				balanceTree(true);
			}
		}
		
		else if (node.getLeftNode() != null ^ node.getRightNode() != null)
		{
			comp++;
		
			if (node.getLeftNode() != null)
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getLeftNode());
					balanceTree(true);
				}
				else 
				{
					root.setRightNode(node.getLeftNode());
					balanceTree(true);
				}
			}
			else
			{
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(node.getRightNode());
					balanceTree(true);
				}
				else 
				{
					root.setRightNode(node.getRightNode());
					balanceTree(true);
				}
			}
		}
		
		else 
		{
			Nodo<T, S> rightNode = node.getRightNode();
			Nodo<T, S> leftNode = node.getLeftNode();
			
			comp++;
			if (root != null)
			{
			
				comp++;
				if (root.getLeftNode() != null && root.getLeftNode() == node)
				{
					root.setLeftNode(null);
				}
				else 
				{
					root.setRightNode(null);
				}
				
				addNodeAVL(leftNode, false);
				addNodeAVL(rightNode, false);
				
				balanceTree(true);
			}
			else
			{
				setRoot(null);
				addNodeAVL(leftNode, false);
				addNodeAVL(rightNode, false);
				
				balanceTree(true);
			}
		}

		super.showRootLevel();
		this.showRotComp();
		this.showTree();
		
		
	}


	private void balanceTree(boolean showMsg)
	{
		balanceTree(getRoot(), showMsg);
	}
	
	private void balanceTree(Nodo<T, S> node, boolean showMsg)
	{
		

		if (node.getLeftNode() == null && node.getRightNode() == null)
		{
			node.setBalance(0);
			node.setLevel(1);
			return;
		}
		
		
		if (node.getLeftNode() != null && node.getLeftNode().getLevel() == -1) balanceTree(node.getLeftNode(), showMsg);
		if (node.getRightNode() != null && node.getRightNode().getLevel() == -1) balanceTree(node.getRightNode(), showMsg);

		if (node.getLeftNode() == null)
		{
			node.setLevel(node.getRightNode().getLevel() + 1);
		}
		else if (node.getRightNode() == null)
		{
			node.setLevel(node.getLeftNode().getLevel() + 1);
		}
		else if (node.getLeftNode().getLevel() >= node.getRightNode().getLevel())
		{
			node.setLevel(node.getLeftNode().getLevel() + 1);
		}
		else
		{
			node.setLevel(node.getRightNode().getLevel() + 1);
		}
		

		int levelLeft = node.getLeftNode() == null ? 0 : node.getLeftNode().getLevel();
		int levelRight = node.getRightNode() == null ? 0 : node.getRightNode().getLevel();
		node.setBalance(levelLeft - levelRight);
		

		if (node.getBalance() == 0 || node.getBalance() == 1 || node.getBalance() == -1) return;

		if (node.getBalance() == 2)
		{
			if (node.getLeftNode().getBalance() == -1)
			{
				leftRotation(node.getLeftNode());
				rot++;
			}
			rightRotation(node);
			rot++;
			resetTree();
			balanceTree(showMsg);
		}
		else
		{
			if (node.getRightNode().getBalance() == 1)
			{
				rightRotation(node.getRightNode());
				rot++;
			}
			leftRotation(node);
			rot++;
			resetTree();
			balanceTree(showMsg);
		}
		
		// Balanced Root
		if (node.getRoot() == null && node.getBalance() != 99) return;
	}

	//mostra o numero de comparações e rotações
	private void showRotComp()
	{
		System.out.println("Rotações: " + rot);
		System.out.println("Comparações: " + comp);
	}
}
