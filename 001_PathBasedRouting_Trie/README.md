# Problem 1: Path-Based Routing using Trie

## Problem Statement

Design a **dynamic URL routing system** (like an API Gateway) that can forward requests to the correct microservice based on the request path.

Example Routes:

- `/api/users/**` → User Service
- `/api/orders/**` → Order Service
- `/api/users/profile/**` → User Profile Service

**Requests:**

| Request Path               | Expected Service         |
|----------------------------|------------------------|
| `/api/users/123`            | User Service           |
| `/api/orders/789`           | Order Service          |
| `/api/users/profile/456`    | User Profile Service   |
| `/api/products/999`         | No Service Found       |

**Goal:**  
Implement the routing system in Java using a **Trie** so that the **longest prefix match** is used for deciding which service should handle the request.

---

## Approach / Explanation

1. **Trie Structure:**  
   - Each node represents a **segment** of the path (`users`, `orders`, etc.).  
   - Nodes that represent **complete routes** store the corresponding **service name**.  

2. **Insertion:**  
   - Split the route into segments by `/`.  
   - Traverse the Trie, creating nodes as needed.  
   - Store the service name at the node corresponding to the **full route**.

3. **Longest Prefix Matching:**  
   - Split incoming request path into segments.  
   - Traverse the Trie segment by segment.  
   - Keep track of the **last node that has a service name**.  
   - Return the service name from the **longest matching prefix**.

4. **Example:**  
   - Request: `/api/users/profile/456`  
   - Traversal path: `api → users → profile → 456`  
   - Last matched service node: `User Profile Service`  

---

## Key Concepts

- **Path-based routing** (used in API Gateways / Microservices)  
- **Longest prefix matching** for selecting the most specific route  
- **Trie (Prefix Tree)** for efficient path lookup  

---
